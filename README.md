# learn-framework-design


## Page Object Model
    WebDriver driver; -> Local driver

    public ProductCatalogue(WebDriver driver) -> driver argument passed from test case
    {
        super(driver); -> Want to pass current driver received from test to parent AbstractComponents
        this.driver = driver;
        PageFactory.initElements(driver, this); -> Need to initialise when using page factory
    }
    @FindBy(css =".mb-3") -> Way to specifying webelement using page factory
    List<WebElement> listOfProducts; -> used in action classes
    By productsBy = By.cssSelector(".mb-3"); -> Some action class required locators instead of element

#### Test Framework strategies 

@Test annotation represent begin / execution start point of one testcase. 
We can keep the similar test cases into the same or one class.

Consider there are 100 scenario are identified to automate. no need to create 100 java class to perform the test.
Categories based on the functionality and other factors and club them into classes.
ex. 5 test scenario on the login page validation 1 class is engouh to hold these 5
assume for 100 testcase 20 class should be fine.

