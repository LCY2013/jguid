package org.fufeng.concurrent.akka;

import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.testkit.typed.javadsl.TestKitJunitResource;
import akka.actor.testkit.typed.javadsl.TestProbe;
import akka.actor.typed.ActorRef;
import org.fufeng.concurrent.cases.actor.FirstActor;
import org.fufeng.concurrent.cases.actor.Greeter;
import org.junit.ClassRule;
import org.junit.Test;

//#definition
public class AkkaQuickstartTest {

    @ClassRule
    public static final TestKitJunitResource testKit = new TestKitJunitResource();
    //#definition

    //#test
    @Test
    public void testGreeterActorSendingOfGreeting() {
        TestProbe<Greeter.Greeted> testProbe = testKit.createTestProbe();
        ActorRef<Greeter.Greet> underTest = testKit.spawn(Greeter.create(), "greeter");
        underTest.tell(new Greeter.Greet("Charles", testProbe.getRef()));
        testProbe.expectMessage(new Greeter.Greeted("Charles", underTest));
    }
    //#test

    @Test
    public void testFirstActor() {
        // 创建actor系统
        final ActorSystem actorSystem = ActorSystem.create("firstActor");
        // 创建FirstActor
        final akka.actor.ActorRef actorRef =
                actorSystem.actorOf(Props.create(FirstActor.class));
        // 发送消息给FirstActor
        actorRef.tell("fufeng", akka.actor.ActorRef.noSender());
    }
}
