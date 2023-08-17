app.controller("order-ctrl",function($scope,$http){
	
	$scope.orderdetails = [];
    $scope.orders = [];
    $scope.form = {};

    $scope.initialize = function(){
        
         // Load order
         $http.get("/rest/orders").then(resp => {
            $scope.orders = resp.data;
        });
    }
    
    //Khởi Đầu
    $scope.initialize();


    //hien thi Order Detail
    $scope.view = function(item){
		 $scope.form = angular.copy(item);     
          $http.get(`/rest/orderdetais/${item.id}`).then(resp => {
            $scope.orderdetails = resp.data;
        });
        $(".nav-tabs a:eq(0)").tab('show');
    }
    
    
    //cap nhat action
	$scope.update = function() {
		var item = angular.copy($scope.form);
		$http.put(`/rest/orders/${item.id}`, item).then(resp => {
			var index = $scope.orders.findIndex(p => p.id == item.id);
			$scope.orders[index] = item;
			 //Khởi Đầu
   			 $scope.initialize();
			alert("Done !");
		}).catch(error => {
			alert("Error !");
			console.log("Error", error);
		});
	}
    
    
      //xoa sp
    $scope.delete = function(item){
        $http.delete(`/rest/orders/${item.id}`).then(resp => {
            var index = $scope.orders.findIndex(o => o.id == item.id);
            $scope.orders.splice(index,1);
            alert("Hủy đơn hàng thành công !");
        }).catch(error => {
            alert("Có Lỗi  !");
            console.log("Error",error);
        });
    }



	
	
//	---------------------------------------------------------------------------------------------- //
	
//	---------------------------------------------------------------------------------------------- //
	
//	---------------------------------------------------------------------------------------------- //
	
//	---------------------------------------------------------------------------------------------- //


});