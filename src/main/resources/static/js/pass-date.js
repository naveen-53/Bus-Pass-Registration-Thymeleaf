function addMonthsPreserveDay(date, months) {
    const originalDay = date.getDate();
    const result = new Date(date.getTime());

    result.setMonth(result.getMonth() + months);

    if (result.getDate() !== originalDay) {
        const year = result.getFullYear();
        const month = result.getMonth();
        const firstOfNext = new Date(year, month + 1, 1);
        firstOfNext.setDate(firstOfNext.getDate() - 1);
        return firstOfNext;
    }

    return result;
}

function formatDateISO(date) {
    const y = date.getFullYear();
    const m = String(date.getMonth() + 1).padStart(2, '0');
    const d = String(date.getDate()).padStart(2, '0');
    return `${y}-${m}-${d}`;
}

document.addEventListener("DOMContentLoaded", function () {
    const startInput = document.getElementById("startDate");
    const endInput = document.getElementById("endDate");

    if (!startInput || !endInput) return;

    function updateEndDate() {
        const startVal = startInput.value;

        if (!startVal) return;

        const [year, month, day] = startVal.split("-").map(Number);
        const startDate = new Date(year, month - 1, day);

        const endDate = addMonthsPreserveDay(startDate, 1);
        endInput.value = formatDateISO(endDate);
    }
    if (startInput.value) updateEndDate();

    startInput.addEventListener("change", updateEndDate);
});
