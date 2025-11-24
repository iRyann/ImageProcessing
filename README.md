# ImageProcessing — 1D/2D Signal Processing Toolkit

> This project is a compact Java/Swing application showcasing core **1D/2D digital signal and image processing techniques**.
> It was developed as part of the *Signal Processing & Neural Networks* course at **HEPL (2025)**.

Although academic, the project is structured as a real engineering codebase: modular, tested, and built with **Java 21 + Maven + JUnit**.

## What This Project Demonstrates

### Core technical skills

* Spatial & frequency-domain filtering (convolution, Fourier-based filters)
* Morphological operations (erosion, dilation, geodesic reconstruction, gradients)
* Edge detection (Sobel, Prewitt, Laplacian, nonlinear operators)
* Histogram processing & tonal curve transformations
* Automatic thresholding & noise reduction
* Complete GUI for interactive processing workflows

### Engineering practices

* Clean module separation (morphology, filtering, Fourier, histograms, etc.)
* Reproducible builds (Maven)
* Automated tests (JUnit)
* Real-time visualization (JFreeChart)

### Relevance to cybersecurity

Signal processing is a backbone in many security domains:

* feature extraction for anomaly / intrusion detection
* noise filtering before classification
* preprocessing steps in biometric/facial/audio analysis
* forensic inspection and reverse-engineering pipelines

This project shows practical understanding of these primitives and how they integrate into larger analytical systems.

## Requirements

* Java 21+
* Maven 3.9+
Dependencies (e.g., JFreeChart) are automatically handled through Maven.
