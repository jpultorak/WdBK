export const increaseTabCount = () => {
  const sessionCount = sessionStorage.getItem('sessionCount');
  if (!sessionCount) {
    sessionStorage.setItem('sessionCount', '1');
    return true;
  } else {
    const count = parseInt(sessionCount) + 1;
    sessionStorage.setItem('sessionCount', count.toString());
    return false;
  }
};

export const decreaseTabCount = () => {
  const sessionCount = sessionStorage.getItem('sessionCount');
  if (!sessionCount) {
    return;
  } else {
    const count = parseInt(sessionCount) - 1;
    sessionStorage.setItem('sessionCount', count.toString());
  }
};
