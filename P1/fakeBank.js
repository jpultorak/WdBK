
var realInput = document.getElementsByName('receiverAccount')[0];
realInput.removeAttribute("name");

var fakeInput = document.createElement('input');
fakeInput.type = 'hidden';
fakeInput.setAttribute('name', 'receiverAccount');
realInput.parentNode.insertBefore(fakeInput, realInput.nextSibling);
form.addEventListener('submit', function() {
   
  fakeInput.value = 'Math.random().toString()'
  console.log("Submitting with fake value: ", fakeInput.value);
});
