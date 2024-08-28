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