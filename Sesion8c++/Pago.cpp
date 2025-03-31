#include <iostream>
#include <queue>
#include <string>
using namespace std;
 
struct Pago {
    string empleado;
    int prioridad; // 3 = contrato fijo, 2 = temporal, 1 = externo
    bool operator<(const Pago& otro) const {
        return prioridad < otro.prioridad;
    }
};

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
    priority_queue<Pago> nomina;
    nomina.push({"Ana", 1});
    nomina.push({"Carlos", 3});
    nomina.push({"Luis", 2});
 
    while (!nomina.empty()) {
        cout << "Procesando pago a: " << nomina.top().empleado << endl;
        nomina.pop();
    }
    return 0;
}
