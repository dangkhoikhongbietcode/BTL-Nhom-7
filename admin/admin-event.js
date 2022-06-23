let data = [{
    name: "Chúc mừng sinh nhật BHD Star",
    date: "Mua 1 vé tặng 1 bỏng",
},
{
    name: "Bé xíu bé xinh",
    date: "Cao dưới 150cm free vé",
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
    // form[1].classList.remove('active')
})

// console.log(input);
adData.addEventListener('click', () => {
    let valName = input[0].value;
    let valDate = input[1].value;

    console.log(input[0].innerHTML);

    let dataBonus = {
        name: valName,
        date: valDate,
    }
    data.push(dataBonus)
    console.log(data);
    render(data)

    input[0].value = "";
    input[1].value = "";
})

input[0].onchange = (e) => {
    // console.log(e.target.value);
}

function render(data) {
    let htmls = data.map((e, index) => {
        return `
       <tr>
        <td id="${index}"scope="row">${index + 1}</td>
        <td>${e.name}</td>
        <td>${e.date}</td>
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
    // let i = document.querySelector('#index').parentElement;
    // const parent = i.querySelectorAll('td');
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
            }
        }
    }
    render(data)
})