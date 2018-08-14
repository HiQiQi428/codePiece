* @version 指定版本信息
* @since 指定最早出现在哪个版本
* @author 指定作者
* @see 生成参考其他的JavaDoc文档的连接
* @link 生成参考其他的JavaDoc文档，它和@see标记的区别在于，@link标记能够嵌入到注释语句中，为注释语句中的特殊词汇生成连接。  eg.{@link Hello}
* @deprecated 用来注明被注释的类、变量或方法已经不提倡使用，在将来的版本中有可能被废弃
* @param 描述方法的参数
* @return 描述方法的返回值
* @throws 描述方法抛出的异常，指明抛出异常的条件

#### Common coding rules for all MBeans
* An MBean must be a concrete Java class. A concrete class is a Java class that is not abstract, and can therefore be instantiated. Remember from chapter 2 that you dynamically loaded the HelloWorld MBean into your simple JMX agent using the HTML adapter. For the agent to successfully create the MBean using reflection, the class name you used had to correspond to a concrete class.
* An MBean must have a public constructor. No additional rules apply to the constructor other than that it must be public. It can have arguments—and the class can contain as many constructors as needed.
* An MBean must implement either its own MBean interface or the ```javax.management.DynamicMBean``` interface. An MBean interface is any interface that follows a naming scheme ClassNameMBean. We will cover MBean interfaces thoroughly in this chapter. MBeans using an MBean interface are Standard MBeans.
#### Concept
* A management interface is the set of methods and attributes exposed by an MBean that management applications can use to manage a resource (via an MBean).
* An MBean interface is any interface that follows the naming convention XXXMBean.
* MBean interfaces must be in the same package as implementation class.
* The management interface of an MBean is composed of the four following items: public constructors, attributes, operations, notifications.
* Setter/Setter correspond to  write/read access to attribute.
* Operations can have multiple parameters and optionally return a value.
* Notifications allow MBeans to communicate with registered listeners.
    > MBean -> ```javax.management.NotificationBroadcaster```
    Agent -> ```javax.management.NotificationListener```

#### Mechanism
* How could a JMX agent find a MBean's attribtues: A JMX agent looks for any method following the getAttributeName() or setAttributeName() naming scheme. In addition to the getAttributeName() pattern, you can optionally use the form isAttributeName(), which must return a boolean value.
    > Note:
    >* If an attribute is exposed with a getter method, it cannot also have an is method. Setter methods also have a unique rule: they cannot be overloaded
    >* Case sensitive: the method setPrintQuality() exposes an attribute PrintQuality, whereas setprintQuality() exposes a different attribute: printQuality.

#### Standard MBean inheritance scenarios
* Direct implementation of an MBean interface: ```HelloWorldMBean <- HelloWorld```
* Inheriting the management interface

# JMX NOTE

### Something
* 资源管理就是支持企业程序和资源管理的方案与工具，大多数网络监视管理系统都使用SNMP作为硬件监控与管理的解决方案，但是SNMP开发并不简单
* JMX主要：监控平台健康状态、配置资源并收集程序统计数据、调试选项、程序性能
* JMX的优点：便于使用、兼容现有的技术、组件化、警告事件统计、快速监控方案

### JMX基本概念
* 可管理资源：可管理资源包跨程序、设备或者可被java访问和包装的实体
* MBean：managed bean，是一个通过管理接口来支持对可管理资源进行访问的java对象
* MBean server：是一个管理着一组MBean的java类，是JMX管理环境的核心件
* JMX agent：作为MBean server的容器，agent是一个提供了一系列MBean管理服务的java进程
* Protocol adapters and connectors
* Management application
* Notification
* Instrumentation

### JMX架构
* Distributed layer
* Agent layer
* Instrumentation layer

### MBean name
"domain:key/value property list", eg: "HelloAgent:name=helloWorld1,port=9092"

### Create a simple MBean management
* create the MBean server and HTML adapter
* register and manage the MBean
* uniquely identify MBeans
* register and start the HTML adapter

### Notification
首先让MBean实现```NotificationBroadcasterrSupport```或者继承```NotificationBroadcaster```，这样可以在MBean内部调用```sendNotification```方法，发送一个通知给所有注册到MBean上的监听器。监听器必须实现```NotificationListener```。

### RMI connector
书上用到了```RmiConnectorServer```，然而jmxtools-1.2.1这个包里已经没有这个类了，而是```RMIConectorServer```替代之，并且```RmiConnectorClient```也换成了```RMIConnector```

## Standard MBean

### MBean rules
* MBean必须是一个java实体类（非抽象、能实例化的类）。为了agent能够成功用反射创建MBean，类名必须与类相对应（？）
* MBean必须有一个公有构造器，这个构造其可以有参数且可以任意重载
* MBean必须实现它自己的接口或者```javax.management.DynamicMBean```。MBean接口必须遵循命名策略```ClassNameMBean````。而使用MBean接口的MBean就是Standard MBean

### RMI
##### Something
* 什么是RPC？RPC 是指计算机 A 上的进程，调用另外一台计算机 B 上的进程，其中 A 上的调用进程被挂起，而 B 上的被调用进程开始执行，当值返回给 A 时，A 进程继续执行。调用方可以通过使用参数将信息传送给被调用方，而后可以通过传回的结果得到信息。而这一过程，对于开发人员来说是透明的。
* 什么是代理和存根？proxy向client提供服务，并从与stub通信进行rpc，stub更像是服务器的代理。当消息到达服务器时，服务器上的操作系统将它传递给服务器存根（server stub）。服务器存根是客户存根在服务器端的等价物，也是一段代码，用来将通过网络输入的请求转换为本地过程调用。服务器stub像socket一样，一般先调用recieve，然后阻塞等待消息到来。
* 为什么使用RPC？首先,程序员可以使用过程调用语义来调用远程函数并获取响应。其次,简化了编写分布式应用程序的难度,因为 RPC 隐藏了所有的网络代码存根函数。应用程序不必担心一些细节,比如 socket、端口号以及数据的转换和解析。在 OSI 参考模型,RPC 跨越了会话层和表示层。
* 什么是RMI？远程方法调用。能够让在客户端Java虚拟机上的对象像调用本地对象一样调用服务端Java虚拟机中的对象上的方法。
* RMI过程：
> 1.客户调用客户端辅助对象stub上的方法
2.客户端辅助对象stub打包调用信息（变量，方法名），通过网络发送给服务端辅助对象skeleton
3.服务端辅助对象skeleton将客户端辅助对象发送来的信息解包，找出真正被调用的方法以及该方法所在对象
4.调用真正服务对象上的真正方法，并将结果返回给服务端辅助对象skeleton
5.服务端辅助对象将结果打包，发送给客户端辅助对象stub
6.客户端辅助对象将返回值解包，返回给调用者
7.客户获得返回值
* RMI与RPC区别：
    * 方法调用方式不同：
        * RMI中是通过在客户端的Stub对象作为远程接口进行远程方法的调用。每个远程方法都具有方法签名。如果一个方法在服务器上执行，但是没有相匹配的签名被添加到这个远程接口(stub)上，那么这个新方法就不能被RMI客户方所调用。
        * RPC中是通过网络服务协议向远程主机发送请求，请求包含了一个参数集和一个文本值，通常形成“classname.methodname(参数集)”的形式。RPC远程主机就去搜索与之相匹配的类和方法，找到后就执行方法并把结果编码，通过网络协议发回。
    * 适用语言范围不同：
        * RMI只适用于java。
        * RPC是网络服务协议，与操作系统和语言无关。
    * 调用结果的返回形式不同：
        * Java是面向对象的，所以RMI的调用结果可以是对象类型或者基本数据类型；
        * RMI的结果统一由外部数据表示 (External Data Representation, XDR) 语言表示，这种语言抽象了字节序类和数据类型结构之间的差异。

##### URL
一个connector server只有一个地址，用JMXServiceURL表示，格式：```service:jmx:protocol:sap```，service:jmx是标准前缀，protocol指定传输协议，sap是服务器地址，格式:```//[host[:port]][url-path]```
##### RMI连接器
JMX API定义了一个标准连接器 - RMI Connector,它支持通过RMI远程访问一个MBeanServer，RMI连接器服务器的地址示例如下：```service:jmx:rmi://localhost:1099/jndi/rmi://localhost:8899/myname```，其中第一个rmi表示连接器使用rmi传输协议，第二个rmi指定rmi注册rmi连接服务器存储存根
###### 创建RMI Connector Server：
<pre><code>MBeanServer server = MBeanServerFactory.createMBeanServer();
HashMap<String, Object> prop = new HashMap<String, Object>();
prop.put(JMXConnectorServer.AUTHENTICATOR, new JMXAuthenticator() {
    public Subject authenticate(Object credentials) {
        if (credentials instanceof String) {
            if (credentials.equals("Hello")) {
                return new Subject();
            }
        }
        throw new SecurityException("not authicated");
    }
});
String url = "service:jmx:rmi:///jndi/rmi://localhost:1099/jmxrmi";
JMXConnectorServer cserver = JMXConnectorServerFactory.newJMXConnectorServer(new JMXServiceURL(url), prop, server);
cserver.start();
</code></pre>

### 当需要MBean与MBean通信时，怎样实现一个含有到其所在server的引用的MBean？
##### 1.为MBean添加一个含有MBean server参数的构造函数
##### 2.实现MBeanRegistration接口
<pre><code>package javax.management;
public abstract interface MBeanRegistration {
  public ObjectName preRegister(MBeanServer server, ObjectName name);
  public void postRegister(Boolean registrationDone);
  public void preDeregister();
  public void postDeregister();
}
</code></pre>

### MBean异常处理
##### 1.抛出异常
JMException是所有jmx异常类的超类，它含有三个子类：
* OperationsException：定义了发生在调用MBean操作期间的异常
* ReflectionException：包装java标准反射异常
* MBeanException：包括所有其他从MBean抛出的异常

### DynamicMBean
MBean必须实现```javax.management.DynamicMBean```接口，记住MBean不能同时实现自定接口（StandardMBean）和DynamicMBean，
##### MBeanInfo
MBeanInfo类是其他描述了部分MBean管理接口的对象的容器，MBeanInfo类和它包含的其他类被统称为MBean元数据类集。DynamicMBean和server一起创建MBean元数据类集
###### MBeanFeatureInfo & MBeanParameterInfo
* MBeanFeatureInfo：每个MBean元数据类都是MBeanFeatureInfo的子类，这个类包含一个name和这个feature的一个描述。
* MBeanParameterInfo：这个类继承了MBeanFeatureInfo并且提供了一个参数到构造器和操作的描述。MBeanParameterInfo对象附加了一个类型字段到将其超类提供的name、description字段。该类型字段包含parameter的类型名称。基于这三个字段，你足以描述一个方法的参数了。

###### MBeanConstructorInfo
描述一个MBean构造器的元数据类。通过使用构造器对象参数或者一个描述构造其属性的MBeanParameterInfo对象数组来创建这个元数据类。因为一个构造器仅仅由其signature描述，所以MBeanConstructor之包含了一个方法（除了继承的）——getSignature()，这个方法返回该构造其的signature作为MBeanParameterInfo对象数组。

###### MBeanAttributeInfo
描述一个MBean属性的元数据类。为了描述MBean暴露的读写属性（只读/只写/读写），该类包含属性的名称、描述和类型。它提供了以前几个方法来获取属性的读写属性：
* getType
* isReadable
* isWritable
* isIs

##### MBeanOperationInfo
* INFO：返回信息，和getter类方法相似
* ACTION：造成MBean某种改变或动作
* ACTION_INFO：INFO和ACTION的结合
* UNKNOWN：操作影响未知

##### MBeanNotificationInfo
该类描述了一个只能有特定MBean发起的notification

### JINI: Java Intelligent Network Infrastructure
>[来源]https://blog.csdn.net/bluejoe2000/article/details/25249515

概念的简明是Jini的目标之一。Jini支持的自发创建群体及自修复等能力，都是基于五个基本的概念：查找（Lookup）、租借（Leasing）、远程事件（Remote Event）、事务（Transaction）。它们以一组软件库和规范的形式实现，可被Jini群体中的参与程序使用。
* 发现：是用于寻找网络中群体并加入它们的过程，是Jini完成自发创建群体功能的部分。
* 查找：是控制提供某个特定服务所需要的程序如何把自己提供给使用服务的参与者的方式。查找在每个Jini群体中的作用相当于目录服务，它提供用于寻找一个群体中已知服务的功能。但查找的功能实际上要比名字服务器复杂得多，名字服务器只是把字符串映像到对象上，而Jini的查找软件可理解Java的类型层次，因此查找操作可在对象类型的基础上进行，而且在搜寻过程中甚至还可以考虑到类的继承关系。
* 租借：是Jini中最重要的概念之一，原因只是由于它被广泛地使用。租借技术使Jini具有自修复能力，它保证了一个群体在某些关键服务失败的情况下，一段时间之后可以恢复。租借还保证了长时间运行的服务（如查找服务）不会"积累"其群体的信息，若没有租借，长时间运行的服务会无限制地增长。
* 远程事件：是Jini服务彼此通报状态的变化所采用的范型。因为查找本身是一类服务，所以在一个群体可用的服务发生变化时，它可以利用远程事件方式通知相关的参与者。Jini的远程事件模型与JavaBeans的事件模型相似，但不完全相同。
* 事务：是Jini使包含多个服务的计算到达一个"安全"状态的机制。这里的意思是，调用者可以得到保证，计算要么全部完成，要么都没有进行，无论哪种情况，系统都到达一个确知的状态。Jini的事务模型可以消除分布式系统中部分失败所带来的危害，从而解决并行性问题，极大改善了服务的健壮性，使服务对网络故障有更大弹性。

什么是JINI？
* 功能类似于（不完全相同）名字服务器，但Jini并不仅仅如此。它是一个用于创建分布式系统的模型，可支持服务在一个群体中自发地出现或消失，而且在系统出错时具有自修复能力。
* JavaBeans的设计核心与Jini完全不同， Beans需要在单地址空间中使用， Bean之间使用的通信机制是基于直接方法调用，而不是远程协议。Beans的模型在灵活性和动态性方面都远不如Jini。当一个新的Bean出现在系统中时，应用中已有的Bean不能立即感知到它并使用它，系统的设计者必须明确地把这个Bean链入应用中并建立与其他Bean的连接。<b>JavaBeans主要是用于设计时而不是运行时，在单地址空间中使用</b>；Jini是用于运行时，跨地址空间工作（Jini可以在很多方面优化JavaBeans）。
* 从表面上看， EJB具有一些Jini的特征，它提供了网络上的服务的概念。企业Bean可以存在于不同的地址空间，并且一般也是如此。<b>但是EJB设计的核心是集中于企业级系统，依靠Java软件来构成企业应用的后端事务逻辑。</b>EJB主要用于支持简便地构造事务逻辑，优化企业网络中已有的事务、消息及数据库服务，因此EJB被大量地用于配置企业软构件之间相对静态路径。只要系统的逻辑不发生变化，也就不需要重新组织Bean之间的连接。同样， EJB是在设计时决定如何连接，<b>而相反Jini是动态的，它在运行时发现服务并在运行时连通它们。</b>
* Jini很大程度上使用了RMI，尤其是在Jini可移动代码的功能上，可以说Jini就是建立在RMI基础上的一组服务和规范。尽管如此， Jini的服务还具有自发连网和自修复能力，虽然也可以（工作量很大）把这些功能加入到通常的RMI应用，但是它们不是RMI本身所具有的，通常的RMI应用也不具有这些优点。
* 从有些方面看，它要比分布式操作系统大，因为有些部分必须运行在至少提供JVM的平台上，而从另外的角度看，它又小得多， Jini所提供的工具以及所使用的概念十分有限。真正的分布式操作系统提供了传统操作系统的所有服务（文件读写、CPU调度、用户登录等），只不过是在一组互连的机器上实现这一功能。Jini允许十分简单的设备加入到Jini群体，而不是要求设备必须运行一个完全的分布式操作系统。

page 148
