#include <iostream>
#include <queue>
#include <string>
using namespace std;
 
struct Siniestro {
    string descripcion;
    int prioridad;
    bool operator<(const Siniestro& otro) const {
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
    priority_queue<Siniestro> cola;
    cola.push({"Robo leve", 1});
    cola.push({"Incendio", 3});
    cola.push({"Accidente vial", 2});
 
    while (!cola.empty()) {
        cout << "Procesando: " << cola.top().descripcion << endl;
        cola.pop();
    }
    return 0;
}
