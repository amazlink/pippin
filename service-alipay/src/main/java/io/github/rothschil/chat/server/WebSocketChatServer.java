package io.github.rothschil.chat.server;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;
import io.github.rothschil.chat.bo.Message;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 聊天服务端
 * @see ServerEndpoint WebSocket服务端 需指定端点的访问路径
 * @see Session   WebSocket会话对象 通过它给客户端发送消息
 *
 *
 * @author <a href="https://github.com/rothschil">Sam</a>
 * @date 2019/10/11 - 20:07
 * @since 1.0.0
 */
@SuppressWarnings("unused")
@Component
@ServerEndpoint("/chat")
public class WebSocketChatServer {

    /**
     * 全部在线会话  PS: 基于场景考虑 这里使用线程安全的Map存储会话对象。
     */
    private static final Map<String, Session> ONLINE_SESSIONS = new ConcurrentHashMap<>();


    /**
     * 当客户端打开连接：1.添加会话对象 2.更新在线人数
     */
    @OnOpen
    public void onOpen(Session session) {
        ONLINE_SESSIONS.put(session.getId(), session);
        sendMessageToAll(Message.jsonStr(Message.ENTER, "", "", ONLINE_SESSIONS.size()));
    }

    /**
     * 当客户端发送消息：1.获取它的用户名和消息 2.发送消息给所有人
     * <p>
     * PS: 这里约定传递的消息为JSON字符串 方便传递更多参数！
     */
    @OnMessage
    public void onMessage(Session session, String jsonStr) {
        Message message = JSON.parseObject(jsonStr, Message.class);
        sendMessageToAll(Message.jsonStr(Message.SPEAK, message.getSender(), message.getMsg(), ONLINE_SESSIONS.size()));
    }

    /**
     * 当关闭连接：1.移除会话对象 2.更新在线人数
     */
    @OnClose
    public void onClose(Session session) {
        ONLINE_SESSIONS.remove(session.getId());
        sendMessageToAll(Message.jsonStr(Message.QUIT, "", "", ONLINE_SESSIONS.size()));
    }

    /**
     * 当通信发生异常：打印错误日志
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    /**
     * 公共方法：发送信息给所有人
     */
    private static void sendMessageToAll(String msg) {
        ONLINE_SESSIONS.forEach((id, session) -> {
            try {
                session.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}