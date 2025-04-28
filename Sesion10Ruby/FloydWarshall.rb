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

def cargar_grafo_floyd(archivo)
  grafo = Hash.new { |h, k| h[k] = {} }
  CSV.foreach(archivo, headers: true) do |row|
    origen, destino, peso = row['Origen'], row['Destino'], row['Peso'].to_i
    grafo[origen][destino] = peso
  end
  grafo
end
 
def floyd_warshall(grafo)
  nodos = grafo.keys | grafo.values.flat_map(&:keys)
  dist = {}
  nodos.each { |i| dist[i] = {}; nodos.each { |j| dist[i][j] = (i == j ? 0 : Float::INFINITY) } }
 
  grafo.each do |u, adyacentes|
    adyacentes.each { |v, peso| dist[u][v] = peso }
  end
 
  nodos.each do |k|
    nodos.each do |i|
      nodos.each do |j|
        dist[i][j] = [dist[i][j], dist[i][k] + dist[k][j]].min
      end
    end
  end
  dist
end
 
grafo = cargar_grafo_floyd('grafo_floyd.csv')
puts floyd_warshall(grafo)
