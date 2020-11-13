function changeCode() {
    let codeImg=document.getElementById("VCode");
    codeImg.src="/CreateVerifyCodeImage?t="+Math.random();
}
///地址没变，但是后面跟的参数r的值变了，每次都有一个随机的数字作为t的值。
///这样做是为了避免浏览器缓存，因为每次的URL都不一致，所以浏览器会从新发请求。
///如果设置这个随机数参数，验证码将不会被点击然后修改，永远是第一次那个验证码图片
///因为地址不变，验证码图片已经被浏览器缓存了

let userName_correct = false;
let passWord_correct=false;
let userCode_correct=false;

function jQueryAjaxLogin() {
    if(!userName_correct || !passWord_correct || !userCode_correct){
        $("#userName").blur();
        $("#passWord").blur();
        $("#userCode").blur();
        return;
    }
    let data;
    if($("#checkBox").prop("checked"))
        data={userName:$("#userName").val(),
            passWord:$("#passWord").val(),
            userCode:$("#userCode").val(),
            checkBox:"y"
            };
    else
        data={userName:$("#userName").val(),
            passWord:$("#passWord").val(),
            userCode:$("#userCode").val()
            };
    $.ajax({
        type:"post",
        url:"/ajaxLoginCheck",
        data:data,
        dataType:"json",
        success:function (response) {
            if(response.code===0)
                window.location.href="/main.jsp";
            else
                $("#checkBoxError").text(response.info);
        }
    });
}

$(document).ready(function () {
    $("#userName").blur(function (e) {
        if($(this).val()==="")
            $("#userNameError").text("用户名不能为空！");
        else{
            $("#userNameError").text("");
            passWord_correct=true;
        }
    })
});


$(document).ready(function () {
    $("#passWord").blur(function (e) {
        if($(this).val()==="")
            $("#passWordError").text("密码不能为空！");
        else{
            $("#passWordError").text("");
            passWord_correct=true;
        }
    })
});

$(document).ready(function () {
    $("#userCode").blur(function (e) {
        if($(this).val()==="")
            $("#userCodeError").text("验证码不能为空！");
        else{
            $("#userCodeError").text("");
            passWord_correct=true;
        }
    })
});



