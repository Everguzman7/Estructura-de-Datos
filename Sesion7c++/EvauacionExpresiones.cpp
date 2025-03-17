#include <iostream>
#include <stack>
#include <ctime>
using namespace std;

void imprimirEncabezado() {
    string nombre = "Ever Rodriguez";
    string campus = "Campus Cali, U. Cooperativa de Colombia";
    string repositorioGit = " ";

    // Obtener la fecha y hora actual
    time_t ahora = time(0);
    tm *ltm = localtime(&ahora);
    char fechaHora[20];
    strftime(fechaHora, sizeof(fechaHora), "%d/%m/%Y %H:%M:%S", ltm);

    // Imprimir el encabezado
    cout << "+----------------------------------------" << endl;
    cout << "| 👤 Nombre: " << nombre << endl;
    cout << "| 🎓 Campus: " << campus << endl;
    cout << "| 📅 Fecha y hora: " << fechaHora << endl;
    cout << "| 📂 Repositorio Git: " << repositorioGit << endl;
    cout << "+----------------------------------------" << endl;
    cout << endl;
}

 
int evaluarPostfija(string expr) {
    stack<int> pila;
    for (char c : expr) {
        if (isdigit(c)) {
            pila.push(c - '0');
        } else {
            int b = pila.top(); pila.pop();
            int a = pila.top(); pila.pop();
            if (c == '+') pila.push(a + b);
            else if (c == '-') pila.push(a - b);
            else if (c == '*') pila.push(a * b);
            else if (c == '/') pila.push(a / b);
        }
    }
    return pila.top();
}
 
int main() {
    imprimirEncabezado();
    cout << evaluarPostfija("23+5*") << endl; // Output: 25
    return 0;
}
