# Project Overview

This is a Java project containing example applications demonstrating basic Java concepts and Swing UI components. Each `.java` file in `src/` is a standalone runnable application.

## Project Structure

- `src/`: Contains all Java source files
  - `array.java`: Demonstrates primitive and String arrays
  - `CalculatorUI.java`: Swing-based calculator interface
  - `StudentInfo.java`: Prints student information
  - `WallpaperExample.java`: Displays an image with overlaid text using Swing

## Building and Running

Compile all sources:
```
javac src/*.java
```

Run individual applications:
```
java -cp src array
java -cp src CalculatorUI
java -cp src QRCodeExample
java -cp src StudentInfo
java -cp src WallpaperExample
```
## Code Conventions

- **Packages**: All classes are in the default package
- **Class Naming**: Inconsistent; some follow PascalCase (e.g., `CalculatorUI`, `StudentInfo`), others lowercase (e.g., `array`)
- **Method Naming**: Mix of camelCase (e.g., `paintComponent` in `WallpaperExample`) and PascalCase (e.g., `Studentname`, `StudentMark` in `StudentInfo`)
- **UI Framework**: Uses Java Swing for graphical interfaces
  - Layout: Absolute positioning for main frame (e.g., `CalculatorUI`), GridLayout for button panels
  - Event Handling: Implements `ActionListener` for button interactions
- **Resource Paths**: Hard-coded absolute paths (e.g., image path in `WallpaperExample`: `"C:\\Users\\patel\\OneDrive\\Pictures\\Purv\\606.jpg"`)
- **Main Methods**: Each class has a `public static void main(String[] args)` for execution

## Dependencies

- Java Standard Library (Swing included)
