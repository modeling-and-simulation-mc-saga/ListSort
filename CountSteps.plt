set terminal pdfcairo enhanced color size 29cm,21cm fontscale 0.5
set key left top
set log xy
set format "10^{%L}"
set xlabel "{/Italic N}"
set ylabel "{/Italic S}({/Italic N})"
set output "CountSteps.pdf"
plot \
"BubbleSort.txt" ps 2 title "Bubble Sort",\
"InsertionSort.txt" ps 2 title "Insersion Sort",\
"SelectionSort.txt" ps 2 title "Selecction Sort",\
"BinaryInsertionSort.txt" ps 2 title "Binary Insersion Sort",\
"MergeSort.txt" ps 2 title "Merge Sort",\
"QuickSort.txt" ps 2 title "Quick Sort",\
x*x/2, x*log(x)