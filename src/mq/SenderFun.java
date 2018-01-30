package mq;

import java.util.Date;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.swing.JOptionPane;
import javax.xml.crypto.Data;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class SenderFun {

	public int send(String name, int count, int gap, String content, String mqip) {
		int successSendCount = 0;
		// ConnectionFactory 连接工厂，JMS 用它创建连接
		ConnectionFactory connectionFactory;
		// Connection JMS 客户端到JMS Provider 的连接
		Connection connection = null;
		// Session 一个发送或接收消息的线程
		Session session;
		// Destination 消息的目的地;消息发送给谁
		Destination destination;
		// MessageProducer消息发送者
		MessageProducer producer;
		// TextMessage message;
		// 构造ConnectionFactory实例对象，此处采用ActiveMq的实现jar
		connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD, "tcp://" + mqip + ":61616");
		try {
			// 构造从工厂得到连接对象
			connection = connectionFactory.createConnection();
			// 启动
			connection.start();
			for (int i = 1; i <= count; i++) {
				if (i % 1000 == 0) {
					try {
						if (null != connection)
							connection.close();
					} catch (Throwable ignore) {
					}
					connection = connectionFactory.createConnection();
					// 启动
					connection.start();
				}
				// 获取操作连接
				session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
				// 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
				destination = session.createQueue(name);
				// 得到消息生成者【发送者】
				producer = session.createProducer(destination);
				// 设置不持久化，此处学习，实际根据项目决定
				producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
				// 构造消息，此处写死，项目就是参数，或者方法获取
				sendMessage(session, producer, count, content, gap);
				session.commit();
				successSendCount++;
				try {
					if (null != session)
						session.close();
				} catch (Throwable ignore) {
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != connection)
					connection.close();
			} catch (Throwable ignore) {
			}
		}
		return successSendCount;
	}

	public void sendInTurn(String name, int count, int gap, String content1, String content2, String mqip) {

		// ConnectionFactory 连接工厂，JMS 用它创建连接
		ConnectionFactory connectionFactory;
		// Connection JMS 客户端到JMS Provider 的连接
		Connection connection = null;
		// Session一个发送或接收消息的线程
		Session session = null;
		// Destination 消息的目的地;消息发送给谁
		Destination destination;
		// MessageProducer消息发送者
		MessageProducer producer;
		// TextMessage message;
		// 构造ConnectionFactory实例对象，此处采用ActiveMq的实现jar
		connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD, "tcp://" + mqip + ":61616");
		try {
			// 构造从工厂得到连接对象
			connection = connectionFactory.createConnection();
			// 启动
			connection.start();
			for (int i = 1; i <= count; i++) {
				// 获取操作连接
				if (i % 1000 == 0) {
					try {
						if (null != connection)
							connection.close();
					} catch (Throwable ignore) {
					}
					connection = connectionFactory.createConnection();
					// 启动
					connection.start();
				}
				session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
				// 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
				destination = session.createQueue(name);
				// 得到消息生成者【发送者】
				producer = session.createProducer(destination);
				// 设置不持久化，此处学习，实际根据项目决定
				producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
				// 构造消息，此处写死，项目就是参数，或者方法获取
				if (i % 2 == 1) {
					sendMessage(session, producer, count, content1, gap);
				} else {
					sendMessage(session, producer, count, content2, gap);
				}
				session.commit();
				try {
					if (null != session)
						session.close();
				} catch (Throwable ignore) {
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != connection)
					connection.close();
			} catch (Throwable ignore) {
			}
		}
	}
	public int sendByFile(String name, int gap, String content, String mqip) {
		int successSendCount = 0;
		ConnectionFactory connectionFactory;
		Connection connection = null;
		Session session;
		Destination destination;
		MessageProducer producer;
		connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD, "tcp://" + mqip + ":61616");
		try {
			connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue(name);
			producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			sendMessage(session, producer, 1, content, 0);
			session.commit();
			successSendCount++;
			try {
				if (null != session)
					session.close();
			} catch (Throwable ignore) {
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != connection)
					connection.close();
			} catch (Throwable ignore) {
			}
		}
		return successSendCount;
	}

	public static void sendMessage(Session session, MessageProducer producer, int count, String content, int gap)
			throws Exception {
		// String stamp=(new Date().getTime())/1000+"";
		// content=content.replaceAll("\"Stamp\":[0-9]{10}","\"Stamp\":"+
		// stamp);
		TextMessage message = session.createTextMessage(content);
		// 发送消息到目的地方
		// System.out.println("发送消息：" + content);
		producer.send(message);
		Thread.sleep(gap);
	}

}
