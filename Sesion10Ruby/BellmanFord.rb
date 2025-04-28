require 'csv'

# Datos del encabezado
nombre = "Ever Rodriguez"
campus = "Campus Cali, U. Cooperativa de Colombia"
repositorio_git = ""

# Obtener la fecha y hora actual
fecha_hora = Time.now.strftime("%d/%m/%Y %H:%M:%S")

# Imprimir el encabezado
puts "+----------------------------------------"
puts "| 👤 Nombre: #{nombre}"
puts "| 🎓 Campus: #{campus}"
puts "| 📅 Fecha y hora: #{fecha_hora}"
puts "| 📂 Repositorio Git: #{repositorio_git}"
puts "+----------------------------------------"
puts
 
def cargar_grafo_bellman(archivo)
  grafo = Hash.new { |h, k| h[k] = {} }
  CSV.foreach(archivo, headers: true) do |row|
    origen, destino, peso = row['Origen'], row['Destino'], row['Peso'].to_f
    grafo[origen][destino] = peso
  end
  grafo
end
 
def bellman_ford(grafo, inicio)
  nodos = grafo.keys | grafo.values.flat_map(&:keys)
  dist = Hash.new(Float::INFINITY)
  dist[inicio] = 0
 
  (nodos.size - 1).times do
    grafo.each do |u, adyacentes|
      adyacentes.each do |v, peso|
        if dist[u] + peso < dist[v]
          dist[v] = dist[u] + peso
        end
      end
    end
  end
 
  # Verificar ciclos negativos
  grafo.each do |u, adyacentes|
    adyacentes.each do |v, peso|
      if dist[u] + peso < dist[v]
        raise #"Ciclo negativo detectado entre #{u} y #{v}\"
      end
    end
  end
 
  dist
end
 
grafo = cargar_grafo_bellman('grafo_bellman.csv')
puts bellman_ford(grafo, 'USD')

