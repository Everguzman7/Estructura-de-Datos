#include <iostream>
#include <string>
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
 
 
struct Especie {
    string nombre;
    Especie* izq;
    Especie* der;
};
 
Especie* nuevaEspecie(string nombre) {
    Especie* e = new Especie();
    e->nombre = nombre;
    e->izq = e->der = nullptr;
    return e;
}
 
Especie* insertar(Especie* raiz, string nombre) {
    if (raiz == nullptr) return nuevaEspecie(nombre);
    if (nombre < raiz->nombre) raiz->izq = insertar(raiz->izq, nombre);
    else raiz->der = insertar(raiz->der, nombre);
    return raiz;
}
 
void inorden(Especie* raiz) {
    if (raiz) {
        inorden(raiz->izq);
        cout << raiz->nombre << " ";
        inorden(raiz->der);
    }
}
 
int main() {
    imprimirEncabezado();
    Especie* raiz = nullptr;
    raiz = insertar(raiz, "Tigre");
    insertar(raiz, "Elefante");
    insertar(raiz, "Mono");
    insertar(raiz, "Cebra");
    insertar(raiz, "Gorila");
 
    cout << "Especies en orden alfabético: ";
    inorden(raiz);
    return 0;
}
