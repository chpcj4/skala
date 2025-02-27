// import { add, divide, multiply, subtract } from './math.js';
// document.getElementById('add').addEventListener('click', () => calculate(add));
// document.getElementById('subtract').addEventListener('click', () => calculate(subtract));
// document.getElementById('multiply').addEventListener('click', () => calculate(multiply));
// document.getElementById('divide').addEventListener('click', () => {
// try {
// calculate(divide);
// } catch (error) {
// alert(error.message);
// }
// });

window.doAdd()

function doAdd() {
    const num1 = parseFloat(document.getElementById('num1').value);
    const num2 = parseFloat(document.getElementById('num2').value);
    const result = num1 + num2
    document.getElementById('result').textContent = result;
}
function doSub() {
    const num1 = parseFloat(document.getElementById('num1').value);
    const num2 = parseFloat(document.getElementById('num2').value);
    const result = num1 - num2
    document.getElementById('result').textContent = result;
}
function doMul() {
    const num1 = parseFloat(document.getElementById('num1').value);
    const num2 = parseFloat(document.getElementById('num2').value);
    const result = num1 * num2
    document.getElementById('result').textContent = result;
}

// function calculate(operation) {
// const num1 = parseFloat(document.getElementById('num1').value);
// const num2 = parseFloat(document.getElementById('num2').value);
// if (isNaN(num1) || isNaN(num2)) {
// alert('유효한 숫자를 입력하세요.');
// return;
// }
// const result = operation(num1, num2);
// document.getElementById('result').textContent = result;
// }
