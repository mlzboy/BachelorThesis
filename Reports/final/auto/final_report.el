(TeX-add-style-hook "final_report"
 (lambda ()
    (LaTeX-add-labels
     "sec-1"
     "sec-2"
     "fig:baidunews"
     "suffixtree:algo:fromroot"
     "suffixtree:algo:findrep"
     "suffixtree:code:equals"
     "suffixtree:code:add"
     "sec-3"
     "sec-4")
    (TeX-run-style-hooks
     "tikz"
     "algpseudocode"
     "algorithmicx"
     "algorithm"
     "amsmath"
     "pbox"
     "booktabs"
     "xeCJK"
     "xltxtra"
     "xunicode"
     "fontspec"
     "listings"
     "hyperref"
     "amstext"
     "amssymb"
     "latexsym"
     "wasysym"
     "marvosym"
     "textcomp"
     "soul"
     "wrapfig"
     "float"
     "longtable"
     "graphicx"
     "fixltx2e"
     "fontenc"
     "T1"
     "inputenc"
     "utf8"
     "latex2e"
     "beamer10"
     "beamer"
     "presentation")))

