set terminal pdfcairo enhanced size 29cm,21cm font "Times-New-Roman" fontscale 1.2
set key left top
set log xy
set format "10^{%L}"
set xlabel "{/:Italic N}"
set ylabel "{/:Italic S}({/:Italic N})"
set output "CountSteps.pdf"
plot \
"BubbleSort.txt" pt 7 ps 2 title "Bubble Sort",\
"InsertionSort.txt" pt 9 ps 2 title "Insertion Sort",\
"SelectionSort.txt" pt 5 lc rgb 'red' ps 2  title "Selecction Sort",\
"MergeSort.txt" pt 11 lc rgb 'orange' ps 2 title "Merge Sort",\
"QuickSort.txt" pt 13 lc rgb 'blue'ps 2 title "Quick Sort",\
x*x/2 lw 2, x*log(x) lw 2