# OpenFoodFacts Spring Boot Starter Maven Package
## Documentation
[![image](https://img.shields.io/badge/GitHub%20Pages-222222?style=for-the-badge&logo=github%20Pages&logoColor=white)](https://alpermulayim.github.io/openfoodfacts-spring-boot-starter/)

**openfoodfacts-spring-boot-starter** Maven package designed to **simplify the integration of OpenFoodFacts REST APIs into Spring Boot** applications. Developers can easily connect to the OpenFoodFacts API without the need to implement any complex RESTful calls.

 ### Framework
 ![image](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
 
 **OpenFoodFactsWebClient**  current version provides different methods for product and search. 

```java
OpenFoodFactsResponse getProduct(String productCode, List<ProductField> fields);
OpenFoodFactsResponse getProduct(String productCode);
OpenFoodFactsResponse getProduct(ProductRequest request);
OpenFoodFactsPageResponse searchProduct(ProductSearchRequest request);
OpenPriceFactsResponse findPrice(PriceRequest priceRequest);
OpenPriceFactsResponse findPrice(String productCode);
ProductSaveResponse saveProduct(ProductSaveRequest request);
String uploadProductImage(ProductImageUploadRequest request);
```

## How to use

**Add github repositories to pom.xml** 

```java
<repositories>
  <repository>
    <id>github</id>
    <url>https://maven.pkg.github.com/AlperMulayim/openfoodfacts-spring-boot-starter</url>
  </repository>
</repositories>
```

**Add openfoodfacts-spring-boot-starter dependecy to your  pom.xml** 

 Please check latest version on [https://github.com/AlperMulayim/openfoodfacts-spring-boot-starter/packages/](https://github.com/AlperMulayim/openfoodfacts-spring-boot-starter/packages/)

```java
<dependency>
  <groupId>com.alpermulayim</groupId>
  <artifactId>openfoodfacts-spring-boot-starter</artifactId>
  <version>0.0.2.5</version>
</dependency>
```

Install the dependencies  

```java
mvn clean install 
cd ~/.m2 // check the repositories downloaded. 
```
**Create configuration file** for OpenFoodFactsWebClientProperties OpenFoodFactsWebClient beans.

```java
@Configuration
public class DemoConfig {

    OpenFoodFactsWebClientProperties properties;

    @Autowired
    public DemoConfig(OpenFoodFactsWebClientProperties properties) {
        this.properties = properties;
    }

    @Bean
    OpenFoodFactsWebClient openFoodFactsWebClient(){
        return new OpenFoodFactsWebClient(properties);
    }
}
```
**Customizable endpoints and paths via application.yml or application.properties files** 

You are able to customize starter endpoints and base urls, if you do not customize webclient have default configuration. 

```java
openfoodfacts:
  base-url: "https://world.openfoodfacts.org"
  prices-base-url: "https://prices.openfoodfacts.org",
  search-path: "/api/v2/search"
  product-path: "/api/v2/product"
  prices-path:"/api/v1/prices"
  
```
## **Ready to use 🎉**

Sample codes and configuration please check [openfoodfacts-starter-demo](https://github.com/AlperMulayim/openfoodfacts-spring-boot-starter/tree/main/openfoodfacts-starter-demo)

Demo Application README: [Demo Application Readme](https://github.com/AlperMulayim/openfoodfacts-spring-boot-starter/blob/main/DemoApp-README.md)
```java
@Service
public class DemoOpenFoodFactsService {
    @Autowired
    OpenFoodFactsWebClient webClient;

    public OpenFoodFactsResponse request(){
			//we are able to create selected fields via ProductField. 
       List<ProductField> fields = new ArrayList<>();
        fields.add(ProductField.PRODUCT_NAME);
        fields.add(ProductField.CODE);
        fields.add(ProductField.NUTRISCORE_SCORE);
        fields.add(ProductField.IMAGE_URL);
        fields.add(ProductField.NUTRISCORE_DATA);
        fields.add(ProductField.NUTRITION_GRADES);
        fields.add(ProductField.BRANDS);
        fields.add(ProductField.INGREDIENTS_TEXT);
       return webClient.getProduct("5449000000996",fields);
    }

    public OpenFoodFactsResponse request(String productCode){
        return webClient.getProduct(productCode);
    }

    public OpenFoodFactsPageResponse search(ProductSearchRequest request)
            throws InvocationTargetException, IllegalAccessException {
        return webClient.searchProduct(request);
    }
    
    public OpenFoodFactsPageResponse searchCustom() throws InvocationTargetException, IllegalAccessException {
        List<ProductField> fields = new ArrayList<>();
        fields.add(ProductField.PRODUCT_NAME);
        fields.add(ProductField.CODE);
        fields.add(ProductField.IMAGE_URL);
        fields.add(ProductField.BRANDS);
        fields.add(ProductField.INGREDIENTS_TEXT);

        ProductSearchRequest request = ProductSearchRequest.builder()
                .brandsTags("nutella")
                .pageSize(3)
                .fields(fields)
                .build();
        return webClient.searchProduct(request);
    }

 public List<DemoProduct> searchDemoProducts() {
	//will request for ETI products total 5. 
	ProductSearchRequest request = searchRequest("eti",5);
	OpenFoodFactsPageResponse response = webClient.searchProduct(request);

	//convert the OpenFoodFacts product to custom product <DemoProduct> 
	return response.products().stream()
		.map(product ->new DemoProduct(product.code(), product.product_name(),product.imageUrl()))
		.collect(Collectors.toList());
    }
}
```
### OpenPrices Demo Usecase
```java
  public OpenPriceFactsResponse getProductPrice(String productCode){
        return webClient.findPrice(productCode);
    }

    public OpenPriceFactsResponse searchPrice(PriceRequest request){
        return webClient.findPrice(request);
    }

    public OpenPriceFactsResponse searchPriceCustom(){
        PriceRequest priceRequest = PriceRequest.builder()
                .priceGt(2.0)
                .priceLt(5.0)
                .size(3)
                .build();
        return webClient.findPrice(priceRequest);
    }

    public List<DemoPrice> myDemoSearchMyProductPrices(){
        PriceRequest priceRequest = PriceRequest.builder()
                .priceGt(2.0)
                .priceLt(5.0)
                .build();

        OpenPriceFactsResponse priceResponse = webClient.findPrice(priceRequest);

        return priceResponse.prices().stream()
                .map(productPrice -> createDemoPrice(productPrice))
                .collect(Collectors.toList());
    }
    private DemoPrice createDemoPrice(ProductPrice productPrice){
        String productName = productPrice.productName();
        String productCode = productPrice.productCode();
        String brand = productPrice.product().brands();
        String country = productPrice.location().osmAddressCountryCode();
        Double price = productPrice.price();
        String currency = productPrice.currency();
        String store = productPrice.location().osmBrand();
        String imageUrl = productPrice.product().imageUrl();

        return new DemoPrice(productCode,productName,brand,store,country,currency,price,imageUrl);
    }
```


### Product Save Demo
``````java
 public ProductSaveResponse saveProductCustom(){
         ProductSaveRequest request = ProductSaveRequest.builder()
                    .code("484848484")
                    .brands("fakebrand-test")
                    .productName("fakeproduct-test")
                    .ingredientsText("fake,ingredients,test1,test2,test3")
                    .comment("fake,comment")
                    .build();
         return webClient.saveProduct(request);
    }
``````

### Image Upload Demo 
```````java
public String uploadProductImage(String code, String lang, String facet, MultipartFile file) throws IOException {

        ProductImageUploadRequest request = ProductImageUploadRequest.builder()
                .productCode(code)
                .language(Language.fromCode(lang))
                .file(file)
                .facet(facet)
                .build();

        return  webClient.uploadProductImage(request);
    }
```````

  ### Author  [Alper Mulayim](https://github.com/AlperMulayim) connect with me!
[![image](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/alpermulayim/) 

