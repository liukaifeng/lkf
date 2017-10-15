package com.lkf.vertx.config;

import io.vertx.core.Vertx;
import io.vertx.rxjava.core.AbstractVerticle;

import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.ext.web.handler.StaticHandler;
import io.vertx.rxjava.ext.web.handler.sockjs.SockJSHandler;
import rx.Observable;
import rx.Subscription;

/**
 * Created by Administrator on 2017/10/14 0014.
 */
public class SocketJSServer  extends AbstractVerticle {

    // Convenience method so you can run it in your IDE
    public static void main(String[] args) {
        // 获取vertx基类
        Vertx vertx = Vertx.vertx();
        // 部署发布rest服务
        vertx.deployVerticle(new SocketJSServer());
    }

    @Override
    public void start() throws Exception {

        Router router = Router.router(vertx);

        router.route("/news-feed/*").handler(SockJSHandler.create(vertx).socketHandler(sockJSSocket -> {

            // Consumer the event bus address as an Observable
            Observable<String> msg = vertx.eventBus().<String>consumer("news-feed")
                    .bodyStream()
                    .toObservable();

            // Send the event to the client
            Subscription subscription = msg.subscribe(sockJSSocket::write);

            // Unsubscribe when the socket closes
            sockJSSocket.endHandler(v -> {
                subscription.unsubscribe();
            });
        }));

        // Serve the static resources
        router.route().handler(StaticHandler.create());

        vertx.createHttpServer().requestHandler(router::accept).listen(8080);

        // Publish a message to the address "news-feed" every second
        vertx.setPeriodic(1000, t -> vertx.eventBus().publish("news-feed", "news from the server!"));
    }
}