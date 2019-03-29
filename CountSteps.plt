set terminal pdfcairo enhanced color size 29cm,21cm fontscale 1.2
set key left top
set log xy
set format "10^{%L}"
set xlabel "{/Italic N}"
set ylabel "{/Italic S}({/Italic N})"
set output "CountSteps.pdf"
plot "BubbleSort.txt" ps 2 pt 9 title "Bubble Sort",\
"MergeSort.txt" ps 2 pt 7 title "Merge Sort",\
x*x/2, x*log(x)