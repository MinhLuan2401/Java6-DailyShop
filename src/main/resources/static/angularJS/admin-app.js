app = angular.module("admin-app",["ngRoute"]);
app.config(function($routeProvider){
    $routeProvider
    .when("/product",{
        templateUrl:"/assets/admin/product/index.html",
        controller:"product-ctrl"
    })
    
    .when("/category",{
        templateUrl:"/assets/admin/category/index.html",
        controller:"category-ctrl"
    })
    
      .when("/authorize",{
        templateUrl:"/assets/admin/authority/index.html",
        controller:"authority-ctrl"
    })
    
      .when("/order",{
        templateUrl:"/assets/admin/order/index.html",
        controller:"order-ctrl"
    })
    
      .when("/topproduct",{
        templateUrl:"/assets/admin/product/topproduct.html",
        controller:"product-ctrl"
    })
    
       .when("/topcustomer",{
        templateUrl:"/assets/admin/authority/topcustomer.html",
        controller:"authority-ctrl"
    })
    
        .when("/result",{
        templateUrl:"/assets/admin/category/result.html",
        controller:"category-ctrl"
    })


    .otherwise({
        templateUrl:"/assets/admin/order/index.html",
        controller:"order-ctrl"
    });
});