function submitTransaction() {
  let sender = document.getElementById("senderInput").value;
  let receiver = document.getElementById("receiverInput").value;
  let amount = document.getElementById("amountInput").value;

  let transaction = {
      sender: sender,
      receiver: receiver,
      amount: parseInt(amount)
  };

  fetch('http://localhost:3000/transactions', {
      method: 'POST',
      headers: {
          'Content-Type': 'application/json',
      },
      body: JSON.stringify(transaction),
  })
  .then(response => response.json())
  .then(data => {
      console.log('Transaction submitted:', data);
      let confirmationMessage = "Transaction Successfull\n" +
                                "Sender: " + sender + "\n" +
                                "Receiver: " + receiver + "\n" +
                                "Amount: $" + amount;
      document.getElementById("confirmation").innerText = confirmationMessage;
  })
  .catch((error) => {
     console.error('Error:', error); 
      let confirmationMessage = "Transaction Failed\n"
      document.getElementById("confirmation").innerText = confirmationMessage;
     
  });
}
