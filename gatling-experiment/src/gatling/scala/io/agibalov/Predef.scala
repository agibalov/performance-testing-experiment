package io.agibalov

import io.gatling.commons.stats.OK
import io.gatling.commons.util.Clock
import io.gatling.core.CoreComponents
import io.gatling.core.action.builder.ActionBuilder
import io.gatling.core.action.{Action, ExitableAction}
import io.gatling.core.config.GatlingConfiguration
import io.gatling.core.protocol.{Protocol, ProtocolComponents, ProtocolComponentsRegistry, ProtocolKey}
import io.gatling.core.session.Session
import io.gatling.core.stats.StatsEngine
import io.gatling.core.structure.ScenarioContext

object Predef {
  def dummy() = DummyProtocolBuilder

  implicit def dummyBuilderToProtocol(builder: DummyProtocolBuilder): DummyProtocol = builder.build()

  def dummy(name: String) = new Dummy(name)
}

class DummyProtocol(val something: String) extends Protocol {
  type Components = DummyComponents
}

object DummyProtocol {
  def apply(something: String) = new DummyProtocol(something)

  val DummyProtocolKey = new ProtocolKey[DummyProtocol, DummyComponents] {
    override def protocolClass: Class[io.gatling.core.protocol.Protocol] =
      classOf[DummyProtocol].asInstanceOf[Class[io.gatling.core.protocol.Protocol]]

    override def defaultProtocolValue(configuration: GatlingConfiguration): DummyProtocol =
      throw new IllegalStateException("Can't provide a default value for DummyProtocol")

    override def newComponents(coreComponents: CoreComponents): DummyProtocol => DummyComponents = {
      dummyProtocol => DummyComponents(dummyProtocol)
    }
  }
}

case class DummyComponents(dummyProtocol: DummyProtocol) extends ProtocolComponents {
  override def onStart: Session => Session = session => session
  override def onExit: Session => Unit = session => {}
}

case class DummyProtocolBuilder(something: String) {
  def build() = DummyProtocol(something)
}

object DummyProtocolBuilder {
  def something(something: String) = DummyProtocolBuilder(something)
}

class Dummy(name: String) {
  def doSomething() = new DummyDoSomethingActionBuilder(name)
}

class DummyDoSomethingActionBuilder(name: String) extends ActionBuilder {
  private def components(protocolComponentsRegistry: ProtocolComponentsRegistry) =
    protocolComponentsRegistry.components(DummyProtocol.DummyProtocolKey)

  override def build(ctx: ScenarioContext, next: Action): Action = {
    import ctx._
    val statsEngine = coreComponents.statsEngine
    val dummyComponents = components(protocolComponentsRegistry)
    new DummyDoSomething(dummyComponents.dummyProtocol, name, statsEngine, coreComponents, next)
  }
}

class DummyDoSomething(protocol: DummyProtocol,
                       val name: String,
                       val statsEngine: StatsEngine,
                       coreComponents: CoreComponents,
                       val next: Action) extends ExitableAction {

  override def clock: Clock = coreComponents.clock

  override def execute(session: Session) = {
    val something = protocol.something // TODO: can use it to configure things
    val codeToTest = new CodeToTest()

    val start = System.currentTimeMillis
    codeToTest.doSomething()
    val end = System.currentTimeMillis

    statsEngine.logResponse(session.scenario, session.groups, name, start, end, OK, None, None)
    next ! session
  }
}
