function signup(e) {
    event.preventDefault();
    var arr = document.getElementsByTagName('input');
    var name =arr[4].value;
    var email  =arr[5].value;
    var passw =arr[6].value;
    var passw1 =arr[7].value;
    var username=document.getElementById("username").value;
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;
    let data=[];
    var user = {
      username: username,
      email: email,
      password: password,
    };
    if (name=="" || email==""|| passw=="" || passw1=="" ){
      alert("Vui lòng điền đầy đủ thông tin")
    }
    else{
    data.push(user);
    var json = JSON.stringify(data);
    localStorage.setItem("data", json);
    alert("dang ky thanh cong");
    }
  }
  
  var user = localStorage.getItem("data");
  console.log(user);
  var data = JSON.parse(user);


  function login1(e) {
    
    event.preventDefault();
    var email2 = document.getElementById("email2").value;
    var password2 = document.getElementById("password2").value;
    console.log(data);
    
    if (
      email2 == data[0].email &&
      password2 == data[0].password
    ) {
      alert("dang nhap thanh cong");
        window.location.href="http://127.0.0.1:5500/index.html"
    } 
    else if(
      email2=="tranvankhanh2812002@gmail.com" &&
      password2=="123456"
      // alert('dang nhap thanh cong');
    ) {
      alert("dang nhap thanh cong");
        window.location.href="http://127.0.0.1:5500/admin/admin.html"
    }
    else {
      alert("dang nhap that bai");
    }
  }


  function login(e) {
    
    event.preventDefault();
    var email1 = document.getElementById("email1").value;
    var password1 = document.getElementById("password1").value;
    console.log(data);
    
    if (
      email1 == data[0].email &&
      password1 == data[0].password
    ) {
      alert("dang nhap thanh cong");
        window.location.href="http://127.0.0.1:5500/index.html"
    } 
    else if(
      email1=="tranvankhanh2812002@gmail.com" &&
      password1=="123456"
      // alert('dang nhap thanh cong');
    ) {
      alert("dang nhap thanh cong");
        window.location.href="http://127.0.0.1:5500/admin/admin.html"
    }
    else {
      alert("dang nhap that bai");
    }
  }