# OpenFoodFacts Spring Boot Starter Maven Package
## Background.

 **openfoodfacts-spring-boot-starter**  maven package implemented for easy connection to OpenFoodFacts  REST API’s on enterprise applications.  

 **openfoodfacts-spring-boot-starter**   provides pre-implemented methods and interfaces for openfoodfacts  REST API communication, easy to integrate your application with maven dependency.  Developers able to implement custom search requests, product field requests, endpoint, base url  customization without  implementation any restful calls. 

 **OpenFoodFactsWebClient**  current version provides different methods for product and search. 

```java
OpenFoodFactsResponse getProduct(String productCode, List<ProductField> fields);
OpenFoodFactsResponse getProduct(String productCode);
OpenFoodFactsResponse getProduct(ProductRequest request);
OpenFoodFactsPageResponse searchProduct(ProductSearchRequest request) ;
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
    <version>0.0.1-SNAPSHOT</version>
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
  search-path: "/api/v2/search"
  product-path: "/api/v2/product"
```
 **Ready to use 🎉**

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
