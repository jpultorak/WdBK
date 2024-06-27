export const increaseTabCount = () => {
  const tabCount = localStorage.getItem('tabCount');
  if (!tabCount) {
    localStorage.setItem('tabCount', '1');
    return true;
  } else {
    const count = parseInt(tabCount) + 1;
    localStorage.setItem('tabCount', count.toString());
    return false;
  }
};
export const getTabCount = () => {
  const tabCount = localStorage.getItem('tabCount');
  if (tabCount == null) {
    return 0;
  }
  return parseInt(tabCount);
};
export const decreaseTabCount = () => {
  const tabCount = localStorage.getItem('tabCount');
  if (!tabCount) {
    return;
  } else {
    const count = parseInt(tabCount) - 1;
    localStorage.setItem('tabCount', count.toString());
  }
};
