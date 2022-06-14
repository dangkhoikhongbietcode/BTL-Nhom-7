let menuBtn = document.querySelector(".ti-menu");
let closeBtn = document.querySelector(".ti-close")
menuBtn.addEventListener('click', function click1(){
  menuBtn.classList.add('close');
 closeBtn.classList.add('open');
})
closeBtn.addEventListener('click', function click2(){
 menuBtn.classList.remove('close');
 closeBtn.classList.remove('open');
})