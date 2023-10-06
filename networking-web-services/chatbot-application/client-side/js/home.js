const baseUrlPath = "http://localhost:8081/chat?question=";

let input = document.getElementById("myInput");
let button = document.getElementById("myButton");
let answer = document.getElementById("answer")
let loading = document.getElementById("loading")

input.addEventListener("keypress", function (event) {
    if (event.key === "Enter") {
        event.preventDefault();
        button.click();
    }
});

// 使用Ajax来修改Web页面的局部内容，而非刷新整个页面
// 前端向后端的Web Service发送请求存在跨域的情况
button.addEventListener("click", function () {
    loading.style.visibility = 'visible'
    $.ajax({
        type: 'POST', // 指定Ajax请求的Request类型
        contentType: 'charset=utf-8',
        url: baseUrlPath + input.value
    }).then(function (data) {
        answer.innerText = data
        loading.style.visibility = 'hidden'
    });
})
