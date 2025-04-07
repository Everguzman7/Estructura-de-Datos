#include <iostream>
#include <stack>
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

 
int main() {
    imprimirEncabezado();
    stack<string> historial;
    historial.push("www.google.com");
    historial.push("www.github.com");
    historial.push("www.stackoverflow.com");
    
    cout << "Página actual: " << historial.top() << endl;
    historial.pop();
    cout << "Retrocediendo... Ahora en: " << historial.top() << endl;
    return 0;
}
