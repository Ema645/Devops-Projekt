const timeElement = document.getElementById('time');
const dateElement = document.getElementById('date');

function updateClock() {
  const now = new Date();

  const time = new Intl.DateTimeFormat('de-DE', {
    hour: '2-digit',
    minute: '2-digit',
    second: '2-digit',
    hour12: false
  }).format(now);

  const date = new Intl.DateTimeFormat('de-DE', {
    weekday: 'long',
    day: '2-digit',
    month: 'long',
    year: 'numeric'
  }).format(now);

  timeElement.textContent = time;
  dateElement.textContent = date;
}

updateClock();
setInterval(updateClock, 1000);
