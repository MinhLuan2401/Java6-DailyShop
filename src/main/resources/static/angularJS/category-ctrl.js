app.controller("category-ctrl",function($scope,$http){

    $scope.cates = [];
    $scope.result = [];
    $scope.form = {};

    $scope.initialize = function(){
 
        // Load categories
         $http.get("/rest/categories").then(resp => {
            $scope.cates = resp.data;
        });
        
        // Load categories
         $http.get("/rest/result").then(resp => {
            $scope.result = resp.data;
        });
        
    }
    //Khởi Đầu
    $scope.initialize();

    // Xóa Form
    $scope.reset= function(){
		$scope.form = {};
    }
    //hien thi len form
    $scope.edit = function(item){
        $scope.form = angular.copy(item);
    }
    //them sp moi
    $scope.create = function(){
	    var item = angular.copy($scope.form);
        $http.post(`/rest/categories`,item).then(resp => {
            $scope.cates.push(resp.data);
            $scope.reset();        
            alert("Thêm mới thành công !");
        }).catch(error => {
            alert("Có Lỗi !");
            console.log("Error",error);
        });
    }
    //cap nhat sp
    $scope.update = function(){
	    var item = angular.copy($scope.form);
        $http.put(`/rest/categories/${item.id}`,item).then(resp => {
            var index = $scope.cates.findIndex(p => p.id == item.id);
            $scope.cates[index] = item;
            $scope.reset();
            alert("Cập nhật loại hàng thành công !");
        }).catch(error => {
            alert("Có Lỗi !");
            console.log("Error",error);
        });
    }
    //xoa sp
    $scope.delete = function(item){
        $http.delete(`/rest/categories/${item.id}`).then(resp => {
            var index = $scope.cates.findIndex(p => p.id == item.id);
            $scope.cates.splice(index,1);
            $scope.reset();
             //Khởi Đầu
   			$scope.initialize();
            alert("Xóa loại hàng thành công !");
        }).catch(error => {
            alert("Có Lỗi !");
            console.log("Error",error);
        });
    }
      

	
//	---------------------------------------------------------------------------------------------- //
	
//	---------------------------------------------------------------------------------------------- //
	
//	---------------------------------------------------------------------------------------------- //
	
//	---------------------------------------------------------------------------------------------- //


});