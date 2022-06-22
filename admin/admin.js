let data = [{
    name1:"Doctor Starnge 2",
    date1:"9/5/2022",
    time1:"12:50",
    cinema1:"BHDPNT",
    room1: "04"
    },
    {
    name2:"Jurassic world dominion",
    date2:"10/6/2022",
    time2:"13:20",
    cinema2:"BHDPNT",
    room2: "02"
    },
    {
    name3:"Em và Trịnh",
    date3:"19/6/2022",
    time3:"9:30",
    cinema3:"BHDPNT",
    room3: "01"
    },

]


const addBtn = document.querySelector('.addBtn');
const closeBtn =document.querySelector('.close')
const form =document.querySelector('.add')
const adData = document.querySelector('.addBtns')
const input =document.querySelectorAll('input')


console.log(addBtn);


addBtn.addEventListener('click',()=>{
    form.classList.add('active')
})
closeBtn.addEventListener('click',()=>{
    form.classList.remove('active')
})

// console.log(input);
adData.addEventListener('click',()=>{
    let valName = input[0].value;
    let valDate = input[1].value;
    let valTime = input[2].value;
    let valCinema = input[3].value;
    let valRoom = input[4].value;

    console.log(input[0].innerHTML);

    let data = [{
        name:valName,
        date:valDate,
        time:valTime,
        cinema:valCinema,
        room: valRoom
    }]
    console.log(data);

    render(data)
})

input[0].onchange = (e)=>{
    // console.log(e.target.value);
}

function render(data){
    let htmls = data.map((e,index)=>{
        return `
       <tr>
        <td scope="row">${index+4}</td>
        <td>${e.name}</td>
        <td>${e.date}</td>
        <td>${e.time}</td>
        <td>${e.cinema}</td>
        <td>${e.room}</td>
        <td>
          <ul>
              <li><button class="admin-btn">Sửa</button></li>
              <li><button class="admin-btn">Xoá</button></li>
          </ul>
      </td>
    </tr>
        `
    })
    document.querySelector('.showdata').innerHTML = htmls.join('')
}