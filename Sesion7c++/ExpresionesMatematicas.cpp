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

 
bool validarExpresion(string expr) {
    stack<char> pila;
    for (char c : expr) {
        if (c == '(') {
            pila.push(c);
        } else if (c == ')') {
            if (pila.empty()) return false;
            pila.pop();
        }
    }
    return pila.empty();
}
 
int main() {
    imprimirEncabezado();
    string expresion = "(5+3)*(2+(4-1))";
    if (validarExpresion(expresion))
        cout << "Expresión válida." << endl;
    else
        cout << "Expresión inválida." << endl;
    return 0;
}


