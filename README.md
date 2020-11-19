## 企业微信app实战

### 框架结构如下

- config
  - ConfigPropInfo  解析配置文件
- core
  - AppOptions  用于配置DesiredCapabilities中的一些参数
  - AppFactory  用于生成AppiumDriver
  - AppBasePage  所有page的父类
- page
  - 用于编写页面



### 编写步骤

#### 页面的编写步骤

每个页面都需要继承AppBasePage，需要在自己的page中实现构造方法，AppBasePage中有两个构造方法，一个无参构造和一个参数为AppniumDriver的构造方法，Page可以实现任意一个

```java
	public AppBasePage() {

	}

	public AppBasePage(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
		AppiumFieldDecorator decorator = new AppiumFieldDecorator(this.driver, Duration.ofMillis(5000));
		PageFactory.initElements(decorator, this);
	}
```

主page(HomePage),可以实现任意一个：

- 如果实现的是无参构造，那么driver的实现则需要在HomePage中去实现，在Test中编写用例时，则不用继承BaseTest去传递driver，可以让底层实现与用例的完全分离，例如：

```java
// HomePage中初始化driver
	public void initDriver(){
		try {
			driver = AppFactory.getDriver();
			AppiumFieldDecorator decorator = new AppiumFieldDecorator(this.driver, Duration.ofMillis(6000));
			PageFactory.initElements(decorator, this);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

// Test中不用传递driver
	@ParameterizedTest
	@MethodSource("initMember")
	@Order(1)
	public void testAddMember(String userName, String userPhone){
		HomePage homePage = new HomePage();
		homePage.contact().addMember(userName, userPhone);
	}
```

- 如果实现的有参的构造，则在编写Test时，需要继承BaseTest，用于传递driver，这样有一个缺点就是底层实现与用例没有完全分离，优点则是可以把用例中共用的方法抽到BaseTest中

```java
public class TestCase2 extends BaseTest {
	
	public void test1(){
		HomePage homePage = new HomePage(getDriver());
		homePage.contact().deleteMember("ceshi001");
	}
}
```

##### 关于元素查找

由于框架封装是基于PageFactory去实现的，所以在编写元素查找时，可以使用注解@AndroidFindBy去实现，例如：

```java
	@AndroidFindBy(xpath = "//*[@text=\"添加成员\"]")
	private WebElement addMemberBtn;
```



### 注意

- 上面的两种方法都可以使用，看个人喜欢
- 框架中有什么不完善或者需要改善的地方，欢迎联系178169143@qq.com