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

void factorial(int n) {
    stack<int> pila;
    int resultado = 1;
    for (int i = n; i > 1; i--) {
        pila.push(i);
    }
    while (!pila.empty()) {
        resultado *= pila.top();
        pila.pop();
    }
    cout << "Factorial: " << resultado << endl;
}
 
int main() {
    imprimirEncabezado();
    factorial(5); // Output: 120
    return 0;
}

