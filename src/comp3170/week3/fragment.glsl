#version 410

in vec3 v_colour;

layout(location = 0) out vec4 color;

void main() {
    color = vec4(v_colour,1);
}

