var firstName = document.querySelector("#name");
var lastName = document.querySelector("#surname");
var userName = document.querySelector("#username");
var psw = document.querySelector("#password");
var pswCheck = document.querySelector("#passwordConfirm");

let btn = document.createElement("button");
btn.innerHTML = "Submit";
btn.classList.add("btn");
btn.classList.add("btn-primary");
btn.type = "submit";

function validation(event, pattern, error){
    if(event.type != 'input'){
        checkValidation();
        return;
    }

    if (event.target.value.length == 0) {
        event.target.classList.remove('is-invalid');
        event.target.classList.remove('is-valid');
        checkValidation();
        return;
    }

    var errorDetail = document.createElement("div");
    errorDetail.classList.add("invalid-feedback");
    errorDetail.innerHTML = error;

    if((pattern).test(event.target.value)){
        if (event.target.classList.contains('is-invalid')) {
            event.target.parentElement.lastElementChild.remove();
            event.target.classList.remove('is-invalid');
        }

        event.target.classList.add('is-valid');
    }else{
        if (event.target.classList.contains('is-valid')) {
            event.target.classList.remove('is-valid');
        }

        if(event.target.nextElementSibling == null){
            event.target.after(errorDetail);
        }

        event.target.classList.add('is-invalid');
    }

    checkValidation();
}

firstName.addEventListener("input", function(event){validation(event, /^[a-zA-Z]+$/g, "Can contain only: {a-z, A-Z}");});
lastName.addEventListener("input", function(event){validation(event, /^[a-zA-Z]+$/g, "Can contain only: {a-z, A-Z}");});
userName.addEventListener("input", function(event){validation(event, /^[a-zA-Z0-9_]+$/g, "Can contain only: {a-z, A-Z, 0-9}");});
psw.addEventListener("input", function(event){validation(event, /^[a-zA-Z0-9_]{8,15}$/g, "Can contain only: {a-z, A-Z, 0-9, _} and must be between 8-15 characters.");});
pswCheck.addEventListener("input", function(event){validation(event, new RegExp("^" + document.getElementById("password").value + "$"), "Must match password.");});

function checkValidation(){
    let form = document.querySelectorAll('.needs-validation')[0];

    var check = true;

    Array.from(form.getElementsByTagName('input')).forEach(input => {
        if(!input.classList.contains("is-valid")){
            check = false;
        }
    })

    if(check){
        document.getElementsByClassName("needs-validation")[0].appendChild(btn);
        form.classList.add('was-validated')
    }
    else{
        if(document.getElementsByClassName("needs-validation")[0].contains(btn))
            document.getElementsByClassName("needs-validation")[0].removeChild(btn);
        if(document.getElementsByClassName("needs-validation")[0].classList.contains("was-validated"))
            form.classList.remove('was-validated')
    }
}