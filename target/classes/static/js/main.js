function openAssignModal(requestId) {
    document.getElementById('requestIdField').value = requestId;
    document.getElementById('assignModal').style.display = 'flex';
}
function closeAssignModal() {
    document.getElementById('assignModal').style.display = 'none';
}
window.onclick = function(event) {
    const modal = document.getElementById('assignModal');
    if (event.target === modal) closeAssignModal();
}
