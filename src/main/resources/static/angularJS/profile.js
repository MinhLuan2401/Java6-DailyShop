app.controller("profile-ctrl",function($scope,$http,$location){
	$scope.admins = [];
	 
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
            $location.path("/unauthorized");
        })
    }

    $scope.authority_of = function(acc,role){
        if($scope.authorities){
            return $scope.authorities.find(ur => ur.account.username == acc.username && ur.role.id==role.id);
        }
    }


	
});