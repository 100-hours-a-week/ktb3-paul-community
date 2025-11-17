class Dog {
    constructor() {
        this.name = "mirae";
        this.type = "spitz";
    }
}



document.addEventListener("DOMContentLoaded", () => {
    const btn = document.getElementById("btn_clickMe");

    btn.addEventListener("click", () => {
        alert("버튼 클릭됨!");
        console.log("버튼이 눌렸습니다");

        const doggy = new Dog();
        console.log(doggy.name);



    });
});



document.addEventListener("DOMContentLoaded", () => {
    const btn = document.getElementById("btn_test2");

    btn.addEventListener("click", () => {
        alert("로그인 버튼 클릭됨");
        console.log("로그인 버튼 클릭됨");


        const doggy = new Dog();
        console.log(doggy.name);



    });
});







