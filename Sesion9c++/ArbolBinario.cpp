#include <iostream>
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
 
struct Nodo {
    int valor;
    Nodo* izquierda;
    Nodo* derecha;
};
 
Nodo* nuevoNodo(int valor) {
    Nodo* nodo = new Nodo();
    nodo->valor = valor;
    nodo->izquierda = nodo->derecha = nullptr;
    return nodo;
}
 
Nodo* insertar(Nodo* raiz, int valor) {
    if (raiz == nullptr) return nuevoNodo(valor);
    if (valor < raiz->valor) raiz->izquierda = insertar(raiz->izquierda, valor);
    else raiz->derecha = insertar(raiz->derecha, valor);
    return raiz;
}
bool buscar(Nodo* raiz, int valor) {
    if (raiz == nullptr) return false;
    if (raiz->valor == valor) return true;
    if (valor < raiz->valor) return buscar(raiz->izquierda, valor);
    else return buscar(raiz->derecha, valor);
}
 
int altura(Nodo* raiz) {
    if (raiz == nullptr) return 0;
    return 1 + max(altura(raiz->izquierda), altura(raiz->derecha));
}
 
int contarHojas(Nodo* raiz) {
    if (raiz == nullptr) return 0;
    if (raiz->izquierda == nullptr && raiz->derecha == nullptr) return 1;
    return contarHojas(raiz->izquierda) + contarHojas(raiz->derecha);
}
 
void inorden(Nodo* raiz) {
    if (raiz != nullptr) {
        inorden(raiz->izquierda);
        cout << raiz->valor << " ";
        inorden(raiz->derecha);
    }
}
 
int main() {
    imprimirEncabezado();
    Nodo* raiz = nullptr;
    raiz = insertar(raiz, 50);
    insertar(raiz, 30);
    insertar(raiz, 70);
    insertar(raiz, 20);
    insertar(raiz, 40);
    insertar(raiz, 60);
    insertar(raiz, 80);
 
    cout << "Inorden: ";
    inorden(raiz);
    cout << "\nBuscar 60: " << (buscar(raiz, 60) ? "Sí" : "No") << endl;
    cout << "Altura del árbol: " << altura(raiz) << endl;
    cout << "Número de hojas: " << contarHojas(raiz) << endl;
    return 0;
}


