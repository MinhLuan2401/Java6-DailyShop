const app = angular.module("shopping-cart",[]);
app.controller("ctrl", function($scope, $http){
	// quản lý giỏ hàng
	$scope.size = 'S';
	$scope.color = 'gray';
	$scope.qty = 1;
	var $cart = $scope.cart = {
        items: [],
        add(id){ // thêm sản phẩm vào giỏ hàng
        	var item = this.items.find(item => item.id == id);
        	alert("\n THÊM GIỎ HÀNG THÀNH CÔNG");
            if(item){
                item.qty++;
                this.saveToLocalStorage();
            }
            else{
            	$http.get(`/rest/products/${id}`).then(resp => {
            		resp.data.qty = $scope.qty;
            		resp.data.size = $scope.size;
            		resp.data.color = $scope.color;
            		this.items.push(resp.data);
            		this.saveToLocalStorage();
            	})
            }
        },
        remove(id){ // xóa sản phẩm khỏi giỏ hàng
        	var index = this.items.findIndex(item => item.id == id);
            this.items.splice(index, 1);
            this.saveToLocalStorage();
        },
        clear(){ // Xóa sạch các mặt hàng trong giỏ
            this.items = []
            this.saveToLocalStorage();
        },
        amt_of(item){ // tính thành tiền của 1 sản phẩm
        	return item.price * item.qty;
        },
        get count(){ // tính tổng số lượng các mặt hàng trong giỏ
            return this.items
            	.map(item => item.qty)
                .reduce((total, qty) => total += qty, 0);
        },
        get amount(){ // tổng thành tiền các mặt hàng trong giỏ
            return this.items
            	.map(item => this.amt_of(item))
                .reduce((total, amt) => total += amt, 0);
        },
        saveToLocalStorage(){ // lưu giỏ hàng vào local storage
        	var json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem("cart", json);
        },
        loadFromLocalStorage(){ // đọc giỏ hàng từ local storage
            var json = localStorage.getItem("cart");
            this.items = json ? JSON.parse(json) : [];
        }
    }
	
	$cart.loadFromLocalStorage();
	
	// Đặt hàng
	$scope.order={ 
		createDate: new Date(),
		address:"",
		ship: $cart.amount+20,
		action:"Chưa Xác Nhận",
		account:{username:$("#username").text()},
		get orderDetails(){
			return $scope.cart.items.map(item => {
				return{
					product:{id: item.id},
					price: item.price,
					quantity:item.qty,
					size:item.size,
					color:item.color
				}	
			});
		},
		purchase(){
			var order = angular.copy(this);
			// thuc hien dat hang
				if($cart.amount==0){
					alert("ERROR")
				}else{
					$http.post("/rest/orders",order).then(resp =>{
				alert("ĐẶT HÀNG THÀNH CÔNG !");
				$scope.cart.clear();
					location.href = "/order/detail/" + resp.data.id;
				}).catch(error =>{
					alert("ĐẶT HÀNG THẤT BẠI !")
					console.log(error)
				})
				}
			
		}
	}
})