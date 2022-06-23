let data = [{
    name: "danhlinh20082002@gmail.com",
    date: "Nguyễn Danh Linh",
    // Họ và tên
    time: "123456", 
    // Mật khẩu
    cinema: "Admin",
    // room: "04"
},
{
    name: "ngdangkhoi261002@gmail.com",
    date: "Nguyễn Đăng Khôi",
    time: "123",
    cinema: "Normal user",
    // room: "02"
},
]

window.addEventListener('DOMContentLoaded', render(data))


const addBtn = document.querySelector('.addBtn');
const closeBtn = document.querySelector('.close')
const form = document.querySelectorAll('.add')
const adData = document.querySelector('.addBtns')
const input = document.querySelectorAll('input')


console.log(addBtn);


addBtn.addEventListener('click', () => {
    form[0].classList.add('active')
})
closeBtn.addEventListener('click', () => {
    form[0].classList.remove('active')
})

// console.log(input);
adData.addEventListener('click', () => {
    let valName = input[0].value;
    let valDate = input[1].value;
    let valTime = input[2].value;
    let valCinema = input[3].value;
    // let valRoom = input[4].value;

    console.log(input[0].innerHTML);

    let dataBonus = {
        name: valName,
        date: valDate,
        time: valTime,
        cinema: valCinema,
        // room: valRoom
    }
    data.push(dataBonus)
    console.log(data);
    render(data)

    input[0].value = "";
    input[1].value = "";
    input[2].value = "";
    input[3].value = "";
    // input[4].value = "";
})


function render(data) {
    let htmls = data.map((e, index) => {
        return `
       <tr>
        <td id="${index}"scope="row">${index + 1}</td>
        <td>${e.name}</td>
        <td>${e.date}</td>
        <td>${e.time}</td>
        <td>${e.cinema}</td>
        <td>
          <ul>
              <li><button class="admin-btn" onclick="editBtn(${index})">Sửa</button></li>
              <li><button class="admin-btn delBtn" onclick="delBtn(${index})">Xoá</button></li>
          </ul>
      </td>
    </tr>
        `
    })
    document.querySelector('.showdata').innerHTML = htmls.join('')
}

// Xoa Data
function delBtn(index) {
    console.log(index);
    data.splice(index, 1);
    render(data)
}

let index;
function editBtn(i) {
    form[1].classList.add('active');
    index = i;
}

document.querySelector('.closeFix').addEventListener('click', () => {
    form[1].classList.remove('active')
})

const inputEdit = document.querySelectorAll('.editInput')

document.querySelector('.fixBtns').addEventListener('click', e => {
    console.log(index);
    for (let i = 0; i < data.length; i++) {
        if (i === index) {
            data[i] = {
                name: inputEdit[0].value,
                date: inputEdit[1].value,
                time: inputEdit[2].value,
                cinema: inputEdit[3].value,
                // room: inputEdit[4].value
            }
        }
    }
    render(data)
})