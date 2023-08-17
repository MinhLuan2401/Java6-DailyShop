app.controller("authority-ctrl",function($scope,$http,$location){
	$scope.admins = [];
	$scope.customers = [];
    $scope.roles = [];
    $scope.authorities = [];

    $scope.initialize = function(){
		
        // Load All Roles
        $http.get("/rest/roles").then(resp => {
            $scope.roles = resp.data;
        })

        //load staffs and directors(administrators)
        $http.get("/rest/accounts?customers=true").then(resp => {
            $scope.admins = resp.data;
        })

        //load authorities of staffs and directors
        $http.get("/rest/authorities?admin=false").then(resp => {
            $scope.authorities = resp.data;
        }).catch(error => {
            alert("CÓ LỖI")
        })
        
         // Load All Top Customer
        $http.get("/rest/topcustomer").then(resp => {
            $scope.customers = resp.data;
        })
    }

    $scope.authority_of = function(acc,role){
        if($scope.authorities){
            return $scope.authorities.find(ur => ur.account.username == acc.username && ur.role.id==role.id);
        }
    }

    $scope.authority_changed = function(acc,role){
        var authority = $scope.authority_of(acc,role);
        if(authority){//da cap quyen => thu hoi quyen(xoa)
            $scope.revoke_authority(authority);
        }
        else{//chua duoc cap quyen => cap quyen(them moi)
            authority = {account:acc,role:role};
            $scope.grant_authority(authority);
        }
    }

    //them moi authority
    $scope.grant_authority = function(authority){
        $http.post(`/rest/authorities`,authority).then(resp => {
            $scope.authorities.push(resp.data)
            alert("CẤP QUYỀN THÀNH CÔNG !");
        }).catch(error => {
            alert("CẤP QUYỀN THẤT BẠI !");
            console.log("Error",error);
        })
    }

    //xoa authority
    $scope.revoke_authority = function(authority){
        $http.delete(`/rest/authorities/${authority.id}`).then(resp => {
            var index = $scope.authorities.findIndex(a => a.id == authority.id);
            $scope.authorities.splice(index,1);
            alert("THU HỒI QUYỀN THÀNH CÔNG !");
        }).catch(error => {
            alert("THU HỒI QUYỀN THẤT BẠI !");
            console.log("Error",error);
        })
    }
    
        //xoa User
    $scope.delete = function(item){
        $http.delete(`/rest/accounts/${item.username}`).then(resp => {
            var index = $scope.admins.findIndex(p => p.username == item.username);
            $scope.admins.splice(index,1);
            $scope.reset();
             //Khởi Đầu
   			$scope.initialize();
            alert("Xóa Thành Công !");
        }).catch(error => {
            alert("Thành công !");
            console.log("Error",error);
        });
    }
    
    
    $scope.initialize();
    
    // Phan trang
    $scope.pager = {
        page: 0,
        size: 10,
        get admins(){
            var start = this.page * this.size;
            return $scope.admins.slice(start,start + this.size);
        },
        get count(){
            return Math.ceil(1.0 *$scope.admins.length / this.size)
        },
        first(){
        	this.page = 0;
        },
        prev(){
        	this.page--;
        	if(this.page<0){
        		this.last();
        	}
        },
        next(){
        	this.page++;
        	if(this.page>=this.count){
        		this.first();
        	}
        },
        last(){
        	this.page = this.count - 1;
        }
	}
	
});